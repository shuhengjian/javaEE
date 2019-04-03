package com.creatorblue.cbitedu.core.interceptor;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.constants.Constant.DATA_SCOPE;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.SpringContextHolder;
import com.creatorblue.cbitedu.system.domain.TsysDataprivilege;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.domain.TsysRole;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.service.TsysDataprivilegeService;
import com.creatorblue.cbitedu.system.service.TsysOrgService;
import com.creatorblue.cbitedu.system.service.TsysRoleService;

import java.util.List;

import javax.servlet.http.HttpSession;


/**
* @ClassName: SelectVisitorImpl
* @Description: 用于修改sql语句的拜访类
* @author tbw
* @date 2014-7-17 下午4:53:49
*/
public class SelectVisitorImpl implements SelectVisitor {
    TsysDataprivilegeService dataprivilegeService;
    AConfig[] aConfigArray;
    AConfig currentConfig;
    TsysOrgService orgService;
    TsysRoleService roleService;

    public SelectVisitorImpl(AConfig[] aConfig) {
        super();
        this.aConfigArray = aConfig;

        if (aConfig.length == 1) {
            currentConfig = aConfig[0];
        }

        dataprivilegeService = SpringContextHolder.getBean(TsysDataprivilegeService.class);
        orgService = SpringContextHolder.getBean(TsysOrgService.class);
        roleService = SpringContextHolder.getBean(TsysRoleService.class);
    }

    @SuppressWarnings("unchecked")
    public void visit(PlainSelect ps) {
        //过滤增强的条件
        Expression enhancedCondition = getEnhancedCondition();

        if (enhancedCondition != null) {
            if (ps.getWhere() != null) {
                Expression expr = new Parenthesis(ps.getWhere());
                AndExpression and = new AndExpression(enhancedCondition, expr);
                ps.setWhere(and);
            } else {
                ps.setWhere(enhancedCondition);
            }
        }
    }

    public Expression getEnhancedCondition() {
        Expression expression = null;

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                               .getRequestAttributes()).getRequest().getSession();

        //获得用户角色
        List<TsysRole> roleList = (List<TsysRole>) session.getAttribute(Constant.USER_DEFAULT_ROLE);

        DATA_SCOPE dataScopeAPI = currentConfig.getDataScope(); //由代码指定的权限类型

        if (dataScopeAPI != null) {
            TsysRole tsysRole = new TsysRole();
            tsysRole.setDataScope(dataScopeAPI.getValue());
            expression = appendDataExp(expression, tsysRole);
        } else {
            for (TsysRole tsysRole : roleList) {
                expression = appendDataExp(expression, tsysRole);
            }
        }

        expression = (expression != null) ? new Parenthesis(expression)
                                          : expression;

        return expression;
    }

    public Expression appendDataExp(Expression expression, TsysRole tsysRole) {
        String dataScope = tsysRole.getDataScope();

        //获得用户
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                               .getRequestAttributes()).getRequest().getSession();
        TsysUserinfo userinfo = (TsysUserinfo) session.getAttribute(Constant.USER_INFO);

        //获得用户角色
        List<TsysRole> roleList = (List<TsysRole>) session.getAttribute(Constant.USER_DEFAULT_ROLE);

        //部门
        List<TsysOrg> orgList = (List<TsysOrg>) session.getAttribute(Constant.USER_DEFAULT_ORG);

        if (userinfo == null) {
            return expression;
        }

        //获得角色权限范围，构造条件
        String orgCol = currentConfig.getOrgCol();

        if (Constant.DATA_SCOPE.ALL.getValue().equals(dataScope)) { //所有权限添加恒成立条件

            EqualsTo exp = new EqualsTo();
            exp.setLeftExpression(new LongValue("1"));
            exp.setRightExpression(new LongValue("1"));
            expression = appendExp(expression, exp);
        } else if (Constant.DATA_SCOPE.SELF.getValue().equals(dataScope)) { //只能看自己的数据

            String userCol = currentConfig.getUserCol();
            EqualsTo exp = new EqualsTo();
            Column column = new Column(new Table(), userCol);
            exp.setLeftExpression(column);
            exp.setRightExpression(new StringValue("'" + userinfo.getUserId() +
                    "'"));
            expression = appendExp(expression, exp);
        } else if (Constant.DATA_SCOPE.OFFICE.getValue().equals(dataScope)) { //所属部门的数据

            for (TsysOrg tsysOrg : orgList) { //循环所在部门

                EqualsTo exp = new EqualsTo();
                Column column = new Column(new Table(), orgCol);
                exp.setLeftExpression(column);
                exp.setRightExpression(new StringValue("'" +
                        tsysOrg.getOrgId() + "'"));
                expression = appendExp(expression, exp);
            }
        } else if (Constant.DATA_SCOPE.OFFICE_AND_CHILD.getValue()
                                                           .equals(dataScope)) { //所属部门及其子机构

            for (TsysOrg tsysOrg : orgList) { //循环所在部门

                String orgId = tsysOrg.getOrgId();
                List<TsysOrg> orgList2 = orgService.selectChildById(orgId);

                for (TsysOrg org2 : orgList2) { //循环子部门

                    EqualsTo exp = new EqualsTo();
                    Column column = new Column(new Table(), orgCol);
                    exp.setLeftExpression(column);
                    exp.setRightExpression(new StringValue("'" +
                            org2.getOrgId() + "'"));
                    expression = appendExp(expression, exp);
                }
            }
        } else if (Constant.DATA_SCOPE.CUSTOM.getValue().equals(dataScope)) { //指定部门的数据

            TsysDataprivilege dataprivilege = new TsysDataprivilege();
            dataprivilege.setRoleId(tsysRole.getRoleId());

            Page page = new Page();
            page.setPagination(false);
            dataprivilege.setPage(page);

            List<TsysDataprivilege> dpList = dataprivilegeService.selectPageTsysDataprivilege(dataprivilege);

            if (!CollectionUtils.isEmpty(dpList)) {
				for (TsysDataprivilege tsysDataprivilege : dpList) { //循环配置的部门权限

					EqualsTo exp = new EqualsTo();
					Column column = new Column(new Table(), orgCol);
					exp.setLeftExpression(column);
					exp.setRightExpression(new StringValue("'"
							+ tsysDataprivilege.getOrgId() + "'"));
					expression = appendExp(expression, exp);
				}
			}else {
				EqualsTo exp = new EqualsTo();
				exp.setLeftExpression(new LongValue("1"));
	            exp.setRightExpression(new LongValue("2"));
				expression = appendExp(expression, exp);
			}
        }

        return expression;
    }

    /**
     * 以or符号连接两个条件
     * @param expression
     * @param expressionAppend
     * @return
     */
    public Expression appendExp(Expression expression,
        Expression expressionAppend) {
        Expression returnExpression = null;

        if (expression == null) {
            returnExpression = expressionAppend;
        } else {
            returnExpression = new OrExpression(expression, expressionAppend);
        }

        return returnExpression;
    }

    public void visit(SetOperationList setoperationlist) {
        List<PlainSelect> selects = setoperationlist.getPlainSelects();

        for (int i = 0; i < selects.size(); i++) {
            this.currentConfig = aConfigArray[i];
            selects.get(i).accept(this);
        }
    }

    public void visit(WithItem withitem) {
        // TODO Auto-generated method stub
    }
}
