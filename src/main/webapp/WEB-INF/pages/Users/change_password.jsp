<%--
  Created by IntelliJ IDEA.
  User: ranid
  Date: 1/14/2023
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/top-nav.jsp"%>

<div class="container">
  <%@include file="../../includes/session_message.jsp"%>
  <div class="p-4 bg-light mb-4">
    <h1 class="">Change Password</h1>
    <!-- Breadcrumb -->
    <nav class="d-flex">
      <h6 class="mb-0">
        <a href="/LMS/admindashboard" class="text-reset">Home</a>
        <span>/</span>
        <a href="/LMS/createbook" class="text-reset"><u>Register User</u></a>
      </h6>
    </nav>
  </div>
  <div class="card">
    <div class="card-body">
      <div class="row p-4">
        <div class="col-3 d-flex align-items-center d-none d-md-block"></div>
        <div class="col-12 col-md-6">
          <form action="/LMS/changepassword" method="POST">
            <div class="form-outline mb-4">
              <input type="password" id="ppassword" name="ppassword" class="form-control" required/>
              <label class="form-label" for="ppassword">Current Password</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" id="password" name="password" class="form-control" required/>
              <label class="form-label" for="password">New Password</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" id="cpassword" name="cpassword" class="form-control" required/>
              <label class="form-label" for="cpassword">Repeat Password</label>
            </div>

            <button type="submit" class="btn btn-success btn-block mb-4">Change Password</button>
          </form>
        </div>
        <div class="col-3 d-flex align-items-center d-none d-md-block"></div>
      </div>
    </div>
  </div>
</div>
<br>
<%@include file="../../includes/footer.jsp"%>