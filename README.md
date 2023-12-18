# BackendExam

How to you make requests:

Address:

Request URL:
http://127.0.0.1:8080/api/addresses

Post request example:
{
  "street": "teststreet",
  "streetNumber": 123,
  "city": "testcity",
  "country": "testcountry"
}

Customer:

Request URL:
http://127.0.0.1:8080/api/customers

Post request example:
{
  "name": "testname",
  "email": "test@mail.com",
  "dob": "2003-08-12",
  "age": 21,
  "addresses": [],
  "orders": []
}

Machine:

Request URL:
http://127.0.0.1:8080/api/machines

Post request example:
{
  "name": "computer",
  "subassemblies": []
}

Order:

Request URL:
http://127.0.0.1:8080/api/orders

Post request example:
{
  "deliveryAddress": {},
  "machines": []
}

PartEntities:

Request URL:
http://127.0.0.1:8080/api/part-entities

Post request example:
{
  "name": "partentity test"
}

Subassembly:

Request URL:
http://127.0.0.1:8080/api/subassemblies

Post request example:
{
  "name": "subessembly test",
  "parts": []
}
