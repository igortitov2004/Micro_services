<!DOCTYPE html>
<html>
<head>
    <title>SHOP</title>
</head>
<body>
<h3>Изменение данных машины</h3>
<hr>
<form action="/eclient/main/save" method="post" enctype="multipart/form-data">
    <div>
        <input type="hidden" value="" required name="id_cars"/><br><br>
        Стоимость:<input type="text"  required name="cost"/><br><br>
        Цвет:<input type="text"  required name="color"/><br><br>
        Вес:<input type="text"  required name="weight"/><br><br>
        Пробег:<input type="number"  required name="mileage"/><br><br>

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