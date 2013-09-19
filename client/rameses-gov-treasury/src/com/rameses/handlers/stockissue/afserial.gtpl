<table>
    <tr>
        <th>Start Series</th>
        <th>End series</th>
        <th>Start Stub</th>        
        <th>End Stub</th>
        <th>Qty</th>
    </tr>
    <%entity.items.each { %>
        <tr>
            <td>${it.startseries}</td>
            <td>${it.endseries}</td>
            <td>${it.startstub}</td>
            <td>${it.endstub}</td>
            <td>${it.qtyissued}</td>
        </tr>
    <% } %>
</table>