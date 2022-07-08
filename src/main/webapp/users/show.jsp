<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 08.07.2022
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<div class="container-fluid">
    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href="<c:url value="/user/add"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50"></i> Add user</a>
    </div>
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">User details</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>ID: ${user.id}</th>
                    </tr>
                    <tr>
                        <th>user name: ${user.name}</th>
                    </tr>
                    <tr>
                        <th>email: ${user.email}</th>
                    </tr>

                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="/footer.jsp" %>
