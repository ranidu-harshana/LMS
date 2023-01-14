<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/top-nav.jsp"%>

<div class="container">
  <%@include file="../../includes/session_message.jsp"%>

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
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${usersList}" varStatus="status">
          <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.address}</td>
            <td>${user.mobno}</td>
            <td>${user.created_at}</td>
            <td>
              <a href="#"><button type="button" class="btn btn-danger btn-sm" data-mdb-toggle="modal" data-mdb-target="#resetPassword${user.id}" ${user.role == 1 ? "disabled" : ""}><i class="fas fa-unlock"></i></button></a>
              <!-- Modal -->
              <div class="modal fade" id="resetPassword${user.id}" tabindex="-1" aria-labelledby="resetPassword${user.id}Label" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="resetPassword${user.id}Label">Reset Password</h5>
                      <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">Are you sure you want to reset the password for this user?</div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Close</button>
                      <form action="/LMS/resetpassword" method="POST">
                        <input type="hidden" value="${user.id}" name="userId">
                        <button type="submit" class="btn btn-danger">Reset</button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </td>
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

