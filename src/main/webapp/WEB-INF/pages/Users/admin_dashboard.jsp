<%--
  Created by IntelliJ IDEA.
  User: ranid
  Date: 1/8/2023
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/top-nav.jsp"%>

<div class="container">
    <div class="p-4 bg-light mb-4">
        <h1 class="">Dashboard</h1>
        <!-- Breadcrumb -->
        <nav class="d-flex">
            <h6 class="mb-0">
                <a href="/LMS/admindashboard" class="text-reset">Home</a>
                <span>/</span>
            </h6>
        </nav>
    </div>
    <div class="card">
        <div class="card-body">
            <div class="row p-4">
                <div class="col-12 col-md-3">
                    <a href="/LMS/books" class="text-decoration-none">
                        <div class="card bg-success text-white">
                            <div class="card-body">
                                <i class="fas fa-book fa-3x"></i><span class="lead fw-bold"> Books</span> <span class="display-6">(03)</span>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-12 col-md-3 mt-3 mt-md-0">
                    <div class="card bg-danger text-white">
                        <div class="card-body">
                            <i class="far fa-bookmark fa-3x"></i><span class="lead fw-bold"> Borrowed </span> <span class="display-6">(03)</span>
                        </div>
                    </div>
                </div>
                <c:if test="${isAdmin}">
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <div class="card bg-warning text-white">
                            <div class="card-body">
                                <i class="fas fa-users fa-3x"></i><span class="lead fw-bold"> Users </span> <span class="display-6">(03)</span>
                            </div>
                        </div>
                    </div>
                </c:if>
<%--                <div class="col-3">--%>
<%--                    <div class="card">--%>
<%--                        <div class="card-body">--%>
<%--                            Hello1--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </div>
        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp"%>