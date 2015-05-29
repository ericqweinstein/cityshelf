CityShelf
=========

<img src='https://pbs.twimg.com/profile_images/493182667282583553/THE24INn.png' width='150'>

## About
CityShelf is a web application that makes searching for books through local independent booksellers quick and easy.

## Running Locally
CityShelf is an isomorphic Clojure(Script) application. The client is written with [ClojureScript](https://github.com/clojure/clojurescript) and [Reagent](https://github.com/reagent-project/reagent), a ClojureScript library for [React](https://github.com/facebook/react); the back end is a simple Clojure web server that delivers the client application to the browser. CityShelf consumes the [Quixote search API](https://github.com/ericqweinstein/quixote) via [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS) in order to locate titles at independent bookstores across the United States.

You'll need the following to run CityShelf locally:

* [Clojure 1.7](http://clojure.org/)
* [Leiningen 2](http://leiningen.org/)

## Installation
1. Clone the CityShelf repository (`λ git clone git@github.com:ericqweinstein/cityshelf.git && cd cityshelf/`)
2. Install dependencies (`λ lein deps`)
3. Start the REPL (`λ lein figwheel`)

## ClojureScript Development
Developing ClojureScript is made leaps and bounds easier by [Figwheel](https://github.com/bhauman/lein-figwheel). If you're working on the ClojureScript code, simply start a REPL:

```bash
λ cd cityshelf/
λ lein figwheel
```

You can now modify your code in the REPL (or your editor) and view changes in the browser&mdash;no reloading required.

## Deploying
CityShelf currently lives on Heroku at http://cityshelf.herokuapp.com/.

1. Build the client application (this automatically lints and tests it) (`λ lein cljsbuild prod`)
2. Deploy (`λ git push heroku master`)

## Contributing
1. Branch (`λ git checkout -b fancy-new-feature`)
2. Commit (`λ git commit -m "Fanciness!"`)
3. Push (`λ git push origin fancy-new-feature`)
4. Ye Olde Pulle Request

## Miscellaneous
You can generate documentation with `λ lein doc`.

## License
MIT (see LICENSE).
