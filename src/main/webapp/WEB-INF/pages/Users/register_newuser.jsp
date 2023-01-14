<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/top-nav.jsp"%>

<div class="container">
  <div class="p-4 bg-light mb-4">
    <h1 class="">Register User</h1>
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
        <div class="col-6 d-flex align-items-center d-none d-md-block">
          <div class="justify-content-center text-center" >
            <img src="<c:url value="/resources/img/Library-bro.png" />" width="80%" alt="add book" class="img-fluid "/>
          </div>
        </div>
        <div class="col-12 col-md-6">
          <form action="/LMS/saveuser" method="POST">
            <div class="form-outline mb-4">
              <input type="text" id="name" name="name" class="form-control" required/>
              <label class="form-label" for="name">Name</label>
            </div>

            <div class="form-outline mb-4">
              <input type="text" id="email" name="email" class="form-control" required/>
              <label class="form-label" for="email">Email</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" id="password" name="password" class="form-control" required/>
              <label class="form-label" for="password">Password</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" id="cpassword" name="cpassword" class="form-control" required/>
              <label class="form-label" for="cpassword">Confirm Password</label>
            </div>

            <div class="form-outline mb-4">
              <textarea class="form-control" id="address" name="address" required rows="5"></textarea>
              <label class="form-label" for="address">Address</label>
            </div>

            <div class="form-outline mb-4">
              <input type="number" id="mobno" name="mobno" class="form-control" required/>
              <label class="form-label" for="mobno">Mob No.</label>
            </div>

            <button type="submit" class="btn btn-success btn-block mb-4">Register</button>
          </form>
        </div>

      </div>
    </div>
  </div>
</div>
<br>
<%@include file="../../includes/footer.jsp"%>