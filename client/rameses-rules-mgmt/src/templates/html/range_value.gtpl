<table border="1" cellpadding="1" cellspacing="0">
    <tr>
        <td colspan="3">Use Variable: ${entity.var.name}</td>
    </tr>
    <tr>
        <th>From</th>
        <th>To</th>
        <th>Value</th>
    </tr>
    <% entity.entries.each { o-> %>
        <tr>
            <td>${(!o.from || o.from == 'null') ?'': o.from}</td>
            <td>${(!o.to || o.from == 'null') ?'': o.to}</td>
            <td>${o.value}</td>
        </tr>
    <%}%>
</table>