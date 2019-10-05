<#import "parts/common.ftl" as c>
<@c.page>
    <div class="card-columns" style="width: 40rem " >
        <#list links as link>
            <div class="card my-3">
                <div class="m-2">
                    <i>${link.getTextLink()}</i>
                </div>
                <div class="card-footer text-muted">
                    <span>${link.getNameBut()}</span>
                    <a href="/link/${link.getId()?c}">Редактировать</a>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>