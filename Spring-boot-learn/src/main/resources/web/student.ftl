<!DOCTYPE html>

<html lang="en">

<body>
<#list studentList as student>
    ${student.id}<br>
    ${student.studentAge}<br>
    ${student.studentName}<br>
    ${student.courseID}<br>
</#list>
</body>

</html>