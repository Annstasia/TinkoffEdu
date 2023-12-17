<table>
  <tr>
    <th>Название способа</th>
    <th>Среднее время работы (ns)</th>
    <td>Отношение к directAccess</td>
  </tr>
  <tr>
    <td>directAccess</td>
    <td>0.641</td>
    <td>1</td>
  </tr>
  <tr>
    <td>method</td>
    <td>8.062</td>
    <td>12,5</td>
  </tr>
  <tr>
    <td>methodHandle</td>
    <td>5.110</td>
    <td>8</td>
  </tr>
  <tr>
    <td>lambdametafactory</td>
    <td>6.280</td>
    <td>9,8</td>
  </tr>
</table>

Итого, methodHandle ближе всего по производитеьности к directAccess, LambdaMetaFactory хуже. Method -
очень медленный.

