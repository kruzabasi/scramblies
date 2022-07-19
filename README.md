## Running the app

Clone the repository:

```
git clone https://github.com/kruzabasi/scramblies.git
cd scramblies

```

Then start the App server:
```
cd scrablies-server
clj -M:scrablies-server
```

And after that start the web UI:
```
cd scrablies-client
npx shadow-cljs watch app
```