

### Technical notes

  * With `RuleReplace6ForAsMany3AsTheValueToTheNthRight` I've tried to 
replace all `if-then-else` with `Either`. The result is a chain of work,
 without any explicit `if` but with implicit conditional behavior

  * In [384e74e4](../../commit/384e74e45538a092f5c716abbb21cb3cc6a8681f), there might be some concept
related to the [Gas][gas] in [Ethereum][ethereum]: an entity needed to be able
to run the function. In this analogy, a `Rule` is similar to a Smart Contract.

[gas]: https://ethereum.stackexchange.com/questions/3/what-is-meant-by-the-term-gas
[ethereum]: http://ethereum.org

