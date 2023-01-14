<%@ page import="com.lms.Helpers" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/top-nav.jsp"%>

<div class="container">
    <%@include file="../../includes/session_message.jsp"%>

    <div class="p-4 bg-light mb-4">
        <h1 class="">All Books</h1>
        <!-- Breadcrumb -->
        <nav class="d-flex">
            <h6 class="mb-0">
                <a href="/LMS/admindashboard" class="text-reset">Home</a>
                <span>/</span>
                <a href="/LMS/books" class="text-reset"><u>All Books</u></a>
            </h6>
        </nav>
    </div>
    <div class="card mb-4">
        <div class="card-body">
            <a href="/LMS/createbook"><button type="button" class="btn btn-success btn-sm"><i class="fas fa-plus-square"></i> Add Book</button></a><br><br>
            <table id="bookListTable" class="table table-striped" style="width:100%">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Author</th>
                    <th>ISBN</th>
                    <th>Publisher Name</th>
                    <th>Publisher Date</th>
                    <th>Available Qty.</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${booksList}" varStatus="status">
                    <tr>
                        <td>${book.name}</td>
                        <td>${book.author}</td>
                        <td>${book.isbn}</td>
                        <td>${book.publisherName}</td>
                        <td>${book.pubDate}</td>
                        <td>${book.availableQty}</td>
                        <td>
                            <div class="btn-group" role="group" aria-label="actions">
                                <c:if test="${isAdmin}">
                                    <a href="#"><button type="button" class="btn btn-danger btn-sm" data-mdb-toggle="modal" data-mdb-target="#deleteBookModal${book.id}" ${isAdmin ? "" : "disabled"}><i class="fas fa-trash-alt"></i></button></a>
                                    <a href="/LMS/editbook/${book.id}" ${isAdmin ? "" : "onclick='return false;'"}><button type="button" class="btn btn-success btn-sm" ${isAdmin ? "" : "disabled"}><i class="fas fa-edit"></i></button></a>
                                    <!-- Modal -->
                                    <div class="modal fade" id="deleteBookModal${book.id}" tabindex="-1" aria-labelledby="deleteBookModal${book.id}Label" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteBookModal${book.id}Label">Delete Book</h5>
                                                    <button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">Are you sure you want to delete this book?</div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Close</button>
                                                    <form action="/LMS/deletebook" method="POST">
                                                        <input type="hidden" value="${book.id}" name="bookId">
                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <a href="/LMS/viewbook/${book.id}" ><button type="button" class="btn btn-primary btn-sm"><i class="fas fa-eye"></i></button></a>
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
        $('#bookListTable').DataTable({
            scrollX: true,
        });
    });
</script>

