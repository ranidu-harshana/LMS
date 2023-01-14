<c:if test="${not empty message}">
  <div class="alert alert-${alert_role}" role="alert">
      ${message}
  </div>
</c:if>
<c:remove var="message" scope="session" />
