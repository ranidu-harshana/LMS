<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/top-nav.jsp"%>

<div class="container">
    <div class="p-4 bg-light mb-4">
        <h1 class="">Edit Book</h1>
        <!-- Breadcrumb -->
        <nav class="d-flex">
            <h6 class="mb-0">
                <a href="/LMS/admindashboard" class="text-reset">Home</a>
                <span>/</span>
                <a href="/LMS/viewbook/${book.id}" class="text-reset"><u>View Book</u></a>
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
                        <div class="form-outline mb-4">
                            <input type="text" id="name" name="name" class="form-control" required value="${book.name}"/>
                            <label class="form-label" for="name">Name</label>
                        </div>

                        <div class="row mb-4">
                            <div class="col">
                                <div class="form-outline">
                                    <input type="text" id="author" name="author" class="form-control" required value="${book.author}"/>
                                    <label class="form-label" for="author">Author</label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-outline">
                                    <input type="text" id="isbn" name="isbn" class="form-control" required value="${book.isbn}"/>
                                    <label class="form-label" for="isbn">ISBN</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-outline mb-4">
                            <input type="text" id="publisherName" name="publisherName" class="form-control" required value="${book.publisherName}"/>
                            <label class="form-label" for="publisherName">Publisher Name</label>
                        </div>

                        <div class="form-outline mb-4">
                            <input type="date" id="pubDate" name="pubDate" class="form-control" required value="${book.pubDate}"/>
                            <label class="form-label" for="pubDate">Publisher Date</label>
                        </div>

                        <div class="form-outline mb-4">
                            <input type="number" id="availableQty" name="availableQty" class="form-control" required value="${book.availableQty}"/>
                            <label class="form-label" for="availableQty">Available Qty.</label>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../includes/footer.jsp"%>