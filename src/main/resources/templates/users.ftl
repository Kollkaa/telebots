<#import "parts/common.ftl" as c>
<@c.page>
    <div class="card-columns" style="width: 40rem " >
        <#list users as use>
            <div class="card my-3">
                <div class="m-2">
                    <i>${use.getChat_id()}</i>
                </div>
                <div class="card-footer text-muted">
                    <#attempt >
                        <span>${use.get()}</span>
                        <#recover >
                        У пользователя нету имени
                    </#attempt>

                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>