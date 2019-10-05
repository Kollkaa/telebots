<#import "parts/common.ftl" as c>
<@c.page>
    Редагування Документів
    <tr>
        <td>
            <form action="/document/documentEditor" method="post" enctype="multipart/form-data">
                <input type="text" name="name" value="${document.name}">
                <input type="text" name="manual" value="${document.manual}">
                <input type="text" name="list_need_document" value="${document.list_need_document}">
                <input type="file" name="file" >
                <input type="hidden" value="${document.id?c}" name="documentId">

                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit">Сохранить</button>
            </form>
        </td>

    </tr>
</@c.page>