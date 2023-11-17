<!DOCTYPE html>
<html>
<head>
    <title>SHOP</title>
    <a href="/main/test">Назад</a>
</head>
<body>
<h3>Изменение данных машины</h3>
<hr>
<form action="/eclient/main/edit" method="post" enctype="multipart/form-data">
    <div><input type="hidden" value="${car.id_cars}" required name="id_cars"/><br><br>
        Стоимость:<input type="text" value="${car.cost}" required name="cost"/><br><br>
        Цвет:<input type="text" value="${car.color}" required name="color"/><br><br>
        Вес:<input type="text" value="${car.weight}" required name="weight"/><br><br>
        Пробег:<input type="number" value="${car.mileage}" required name="mileage"/><br><br>
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