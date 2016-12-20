
val regexp = """(.+vtexcommercebeta.com.br$|.+qa.com.br$)"""

val positiveUrls = List(
  "foo.vtexcommercestable.com.br",
  "instoreqa.com.br"
)

positiveUrls.forall(str => str.matches(regexp))