<#import "parts/common.ftl" as c>
<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить сылку
    </a>
    <div class="collapse big-banner"   id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/link/addLink">
                <input type="text" name="links" placeholder="сылка">
                <input type="text" name="name" placeholder="название">

                <select name="cities">
                    <#list cities as city >
                            <option value="${city.getName()}" >${city.getName()}</option>
                    </#list>
                </select>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>            </form>
        </div>
    </div>


    <div class="card-columns" style="width: 40rem " >
        <#list links as link>
            <div class="card my-3">
                <div class="m-2">
                    <i>${link.getTextLink()}</i>
                </div>
                <div class="card-footer text-muted">
                    <span>${link.getNameBut()}</span>
                   <#attempt >
                       <span>${link.getCity().getName()}</span>
                       <#recover>
                   </#attempt>
                    <a href="/link/${link.getId()?c}">Редактировать</a>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>