# AI Shop

AI Shop is a playground used during Levi9 meetup to demonstrate how AI 
can be used to extract products from a database based on user requests.

## An online shop that uses AI to extract products on customer request

It's and experimental project fot Levi9 meetup.
It parses user request and handles it in two different ways.

1. Generates SQL, that returns list of products which is directly executed.
   Can be found in [product.ai.sql](src%2Fmain%2Fjava%2Flevi9%2Fmeetup%2Fai%2Fproduct%2Fai%2Fsql) package.

2. Generates DTO which is passed to already existing repository.
   Can be found in [product.ai.filter](src%2Fmain%2Fjava%2Flevi9%2Fmeetup%2Fai%2Fproduct%2Fai%2Ffilter) package.

## Technologies

This example uses the [langchain4j](https://docs.langchain4j.dev/) framework to build the AI application.
Please consult the documentation for usage details.

## Running the application

Prior to running the application, you need to start the PostgreSQL database.
The easiest way to do this is to use included `docker-compose.yml` file.

```bash
docker-compose up
```

Once the database is running, you can start the application using:

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.
You can now query the endpoints to run AI-powered product searches.

Query the `/product/ai/sql` endpoint to try and generate SQL for product search.

Example:

```curl
curl -X POST --location "http://localhost:8080/products/ai/sql" \
    -H "Content-Type: application/json" \
    -d 'Find all electronics suitable for running in US power grid.'
```

Similarly, you can query the `/product/ai/dto` endpoint to generate a DTO for product search.

```curl
curl -X POST --location "http://localhost:8080/products/ai/sql" \
    -H "Content-Type: application/json" \
    -d 'Find products using 15W power supply.'
```

## Using different AI providers

The application supports running with Ollama or OpenAI models.
By default, it uses OpenAI. 
While the results are better with OpenAI, you'll need to retrieve and set up an OpenAI API keys.

### Using OpenAI

You'll need to get access to [OpenAI API](https://platform.openai.com/settings), create a project and register your API key.
In case you have issues connecting to OpenAI please check:

- Whether you have some credits left on your account.
- Whether you allowed the usage of the desired model in the `Limits` section of the OpenAI dashboard.

### Using Ollama

Using Ollama is a free alternative to OpenAI.
While the results are not as good, it can be used free of charge and without tinkering with API keys.

To use Ollama, you need to install it and run the following command to pull the model:

```bash
ollama serve
ollama pull mistral
```

Change the `chatModel` parameter in `@AiService` annotation to `ollamaChatModel` in this interfaces:

- `levi9.meetup.ai.product.ai.sql.SqlAgent`
- `levi9.meetup.ai.product.ai.filter.BuildingDtoAgent`


