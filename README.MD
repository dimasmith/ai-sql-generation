### An online shop that uses AI to extract products on customer request

It's and experimental project fot Levi9 meetup.
It parses user request and handles it in two different ways.

1. Generates SQL, that returns list of products which is directly executed.
   Can be found in [product.ai.sql](src%2Fmain%2Fjava%2Flevi9%2Fmeetup%2Fai%2Fproduct%2Fai%2Fsql) package.

2. Generates DTO which is passed to already existing repository.
   Can be found in [product.ai.filter](src%2Fmain%2Fjava%2Flevi9%2Fmeetup%2Fai%2Fproduct%2Fai%2Ffilter) package.
