$(document).ready(function() {
    $('#example').DataTable( {
        "processing": true,
        "serverSide": true,
        "autoWidth": false,
        "ajax": {
            "url": "http://localhost:8087/dashboard/worktime",
            "type": "POST"
        },
        "columns": [
            { "data": "empNo" },
            { "data": "name" },
            { "data": "date_Worktime" },
            { "data": "checkIn" },
            { "data": "checkOut" },
            { "data": "numberJobs" },
            { "data": "hours" },
            { "data": "numberJobsPlus" },
            { "data": "hoursPlus" },
            { "data": "lateEntry" },
            { "data": "outEarly" },
            { "data": "nameShift" },
            { "data": "total" },
            { "data": "note" }
        ]
    } );
} );