<!DOCTYPE html>
<html>
<#import "spring.ftl" as spring />
<head>
    <title>SHOP</title>
    <a href="/main/test">Назад</a>
</head>
<body>
<h3>Добавление данных машины</h3>
<hr>

    <form action="/eclient/main/save" method="post" enctype="multipart/form-data">
        <div>
            <input type="hidden" value="" required name="id_cars"/><br><br>
            Стоимость:<input type="text" value="${carDetail.cost}"  name="cost"/><br>
<#--            <#if err??>-->
<#--            <div style="color: red">-->
<#--                <@spring.bind "carDetail.cost"/>-->
<#--                <@spring.showErrors ""/>-->
<#--            </div>-->
<#--&lt;#&ndash;               <#else>&ndash;&gt;-->
<#--&lt;#&ndash;                   Стоимость:<input type="text" required  name="cost"/><br>&ndash;&gt;-->
<#--            </#if>-->
            <br>
            Цвет:<input type="text"  value="${carDetail.color}"  name="color"/><br>
<#--            <#if err??>-->
<#--                <div style="color: red">-->
<#--                    <@spring.bind "carDetail.color"/>-->
<#--                    <@spring.showErrors ""/>-->
<#--                </div>-->
<#--            </#if>-->
<#--            <br>-->
            Вес:<input type="text"  value="${carDetail.weight}"  name="weight"/><br>
<#--            <#if err??>-->
<#--            <div style="color: red">-->
<#--                <@spring.bind "carDetail.weight"/>-->
<#--                <@spring.showErrors ""/>-->
<#--            </div>-->
<#--            </#if>-->
<#--            <br>-->
            Пробег:<input type="number"  value="${carDetail.mileage}"  name="mileage"/><br>
<#--            <#if err??>-->
<#--            <div style="color: red">-->
<#--                <@spring.bind "carDetail.mileage"/>-->
<#--                <@spring.showErrors ""/>-->
<#--            </div>-->
<#--            </#if>-->
<#--            <br>-->


            <#--        <select name="job_title" required >-->
            <#--            <option value="${staff.job_title}" >${staff.job_title}</option>-->
            <#--            <option value="Менеджер"> Менеджер</option>-->
            <#--            <option value="Продавец-кассир"> Продавец-кассир</option>-->
            <#--        </select><br><br>-->
        </div>
        <input type="submit" value="Применить"/>
    </form>


</body>
</html>