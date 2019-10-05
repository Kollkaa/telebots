<#import "parts/common.ftl" as c>
<@c.page>
<form action="/link" method="post">
    <div class="card-columns" style="width: 40rem " >
            <div class="card my-3">
                <div class="m-2">
                    <input name="text" value="${link.getTextLink()}">
                </div>
                <div class="card-footer text-muted">
                    <span>${link.getNameBut()}</span>
                </div>
            </div>
    </div>
    <input type="hidden" value="${link.getId()?c}" name="linkId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Сохранить</button>
</form>
</@c.page>