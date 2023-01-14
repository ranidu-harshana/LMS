<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/top-nav.jsp"%>

<div class="container">
  <c:if test="${not empty message}">
    <div class="alert alert-${alert_role}" role="alert">
        ${message}
    </div>
  </c:if>
  <c:remove var="message" scope="session" />

  <div class="p-4 bg-light mb-4">
    <h1 class="">All Users</h1>
    <!-- Breadcrumb -->
    <nav class="d-flex">
      <h6 class="mb-0">
        <a href="/LMS/admindashboard" class="text-reset">Home</a>
        <span>/</span>
        <a href="/LMS/users" class="text-reset"><u>All Users</u></a>
      </h6>
    </nav>
  </div>
  <div class="card mb-4">
    <div class="card-body">
      <a href="/LMS/registeruser"><button type="button" class="btn btn-success btn-sm"><i class="fas fa-plus-square"></i> Register User</button></a><br><br>
      <table id="userListTable" class="table table-striped" style="width:100%">
        <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Address</th>
          <th>Mob No</th>
          <th>Created At</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${usersList}" varStatus="status">
          <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.address}</td>
            <td>${user.mobno} ${user.role}</td>
            <td>${user.created_at}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
<%@include file="../../includes/footer.jsp"%>
<script>
  $(document).ready(function () {
    $('#userListTable').DataTable({
      scrollX: true,
    });
  });
</script>

