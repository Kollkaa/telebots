<#import "parts/common.ftl" as c>
<@c.page>
    Перегляд документів
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить документ
    </a>
    <div class="collapse big-banner"  style="height: 500px ;width: 920px;" id="collapseExample">
        <div class="form-group mt-3">
        <form action="/document/documentAdd" method="post" enctype="multipart/form-data">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Назва :</label>
            <div class="col-sm-6">
                <input type="text" name="name" class="form-control" placeholder="Назва" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Опис :</label>
            <div class="col-sm-6">
                <input type="text" name="manual" class="form-control" placeholder="Опис" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Що необхідно для оформлення:</label>
            <div class="col-sm-6">
                <input type="text" name="list_need_document" class="form-control" placeholder="Що необхідно для оформлення" />
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">фото :</label>
            <div class="col-sm-6">
                <input type="file" name="file" class="form-control" placeholder="фото" />
            </div>
        </div>

        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Добавить</button>
        </form>
        </div>
    </div>
    <div class="card-columns" style="width: 40rem " >
        <#list documents as document>
            <div class="card my-3">
                <#attempt >

                <#if document.getFoto()??>
                    <img src="/photos/${document.getFoto()}" class="card-img-top">
                </#if>
                    <#recover>
                </#attempt>
                <div class="m-2">
                    <span>${document.manual}</span>
                    <i>${document.list_need_document}</i>
                </div>
                <div class="card-footer text-muted">
                    ${document.getName()}
                    <a href="/document/${document.getId()?c}">Редактировать</a>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>