#!/bin/bash



if [ -z "$1" ]; then
  ct=100
else
  ct="$1"
fi 

echo "param: $ct"


curl 'https://www.megamillions.com/cmspages/utilservice.asmx/GetDrawingPagingData' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:74.0) Gecko/20100101 Firefox/74.0' -H 'Accept: application/json' -H 'Accept-Language: en-US,en;q=0.5' -H 'Content-Type: application/json; charset=utf-8' -H 'X-Requested-With: XMLHttpRequest' -H 'Origin: https://www.megamillions.com' -H 'Connection: keep-alive' -H 'Referer: https://www.megamillions.com/Winning-Numbers/Previous-Drawings.aspx?pageNumber=1&pageSize=20&startDate=02/01/2010&endDate=03/15/2020' -H 'Cookie: __cfduid=d05df6e178aa44c054cd2491720ceb5ae1584278155; CMSPreferredCulture=en-US; CMSCsrfCookie=yoOLP6sQzXH4tp66NZPzW3zdGwyzwUgCGiNTXPRL; ASP.NET_SessionId=lk2rmwbha2al50e3jypl3oyu; _gcl_au=1.1.1898109864.1584278156; _ga=GA1.2.1598971614.1584278157; _gid=GA1.2.825259742.1584278157; __gads=ID=047e17c2efd9ca4e:T=1584278157:S=ALNI_MZvJWBTgGS7FtxlHmdmnyz3nayQLg; _gat_UA-130954248-1=1' -H 'Pragma: no-cache' -H 'Cache-Control: no-cache' --data "{\"pageSize\":${ct},\"startDate\":\"02/01/2010\",\"endDate\":\"03/15/2020\",\"pageNumber\":2}" | sed 's/\\//g' | sed 's/\"{/{/g' | sed 's/}\"/}/g' | jq -c '.d.DrawingData' > mega_millions_numbers.dat

