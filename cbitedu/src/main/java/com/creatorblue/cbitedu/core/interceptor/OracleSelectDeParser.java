package com.creatorblue.cbitedu.core.interceptor;

import java.util.Iterator;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;

import org.springframework.util.StringUtils;

public class OracleSelectDeParser extends SelectDeParser {

    public OracleSelectDeParser(ExpressionDeParser expressionDeParser, StringBuilder sb) {
        super(expressionDeParser, sb);
    }

    /**
     * 重写父类方法，去掉父类方法中table前的as
     */
    public void visit(Table tableName) {
        getBuffer().append(tableName.getFullyQualifiedName());
        String alias =tableName.getAlias()!=null? tableName.getAlias().getName():null;
        if (alias != null && !StringUtils.isEmpty(alias)) {
            getBuffer().append(" ");
            getBuffer().append(alias);
        }
    }

    /**
     * 重写父类方法，在JOIN之前增加空格
     */
    @SuppressWarnings("unchecked")
    public void deparseJoin(Join join) {
        if (join.isSimple()) {
            getBuffer().append(", ");
        } else {
            getBuffer().append(" ");
            if (join.isRight()) {
                getBuffer().append("RIGHT ");
            } else if (join.isNatural()) {
                getBuffer().append("NATURAL ");
            } else if (join.isFull()) {
                getBuffer().append("FULL ");
            } else if (join.isLeft()) {
                getBuffer().append("LEFT ");
            }
            if (join.isOuter()) {
                getBuffer().append("OUTER ");
            } else if (join.isInner()) {
                getBuffer().append("INNER ");
            }
            getBuffer().append("JOIN ");
        }

        FromItem fromItem = join.getRightItem();
        fromItem.accept(this);
        if (join.getOnExpression() != null) {
            getBuffer().append(" ON ");
            join.getOnExpression().accept(getExpressionVisitor());
        }
        if (join.getUsingColumns() != null) {
            getBuffer().append(" USING ( ");
            for (Iterator<Column> iterator = join.getUsingColumns().iterator(); iterator.hasNext();) {
                Column column = iterator.next();
                getBuffer().append(column.getFullyQualifiedName());
                if (iterator.hasNext()) {
                    getBuffer().append(" ,");
                }
            }
            getBuffer().append(")");
        }
    }
    @Override
    public void visit(SetOperationList list) {
    	super.visit(list);
    }
}

