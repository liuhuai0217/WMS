<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<s:property value="#pageResult.totalCount"/>
						条记录，当前第
						<s:property value="#pageResult.currentPage"/>/<s:property value="#pageResult.totalPage"/>
						页
					</div>
					<div class="ui_frt">
						<input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
						<input type="button" value="上一页" class="ui_input_btn01 btn_page" data-page="<s:property value="#pageResult.prevPage"/>"/>
						<input type="button" value="下一页" class="ui_input_btn01 btn_page" data-page="<s:property value="#pageResult.nextPage"/>"/>
						<input type="button" value="尾页" class="ui_input_btn01 btn_page"data-page="<s:property value="#pageResult.totalPage"/>"/>
			
						<s:select list="{10,20,50}" name="qo.pageSize" cssClass="ui_select02"/>
						转到第
							<s:textfield name="qo.currentPage" cssClass="ui_input_txt01"/>页
							 <input type="button" class="ui_input_btn01 btn_go" value="跳转"/>
					</div>
				</div>
    