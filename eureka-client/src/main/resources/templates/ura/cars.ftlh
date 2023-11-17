<!DOCTYPE html>
<html>
<head>

    <title>QWER</title>
</head>
<body>
<form action="/main/test" method="get">
    <div style="display:inline-flex">Поиск по цвету:<input type="text" name="color">
        <input type="submit" value="Поиск"/></div>
</form><br>
<table border="1">
    <tr>
        <th> ID машины </th>
        <th> Пробег </th>
        <th> Вес </th>
        <th> Цвет </th>
        <th> Стоимость </th>
        <th> Удалить </th>
        <th> Изменить </th>
    </tr>
<#list cars as car>
    <tr>
        <th>${car.id_cars}</th>
        <th>${car.mileage}</th>
        <th>${car.weight}</th>
        <th>${car.color}</th>
        <th>${car.cost}</th>
        <th><form action="/eclient/main/deleteCar/${car.id_cars}"  method="post" >
                <input type="submit" value="Удалить"/>
            </form></th>
        <th><form action="/eclient/main/edit/${car.id_cars}"  method="get" >
                <input type="submit" value="Изменить"/>
            </form></th>
    </tr>
</#list>
    </table>
<br>

<form action="/eclient/main/startSave"  method="get" >
<input type="submit" value="Добавить машину"/>
</form><br>

<form action="/eclient/main/sendMail"  method="get" >
    Введите почту:<input type="email" required name="email">
    <input type="submit" value="Получить письмо"/>
</form>
</body>
</html>