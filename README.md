## Running the app

Clone the repository:

```
git clone https://github.com/kruzabasi/scramblies.git
cd scramblies

```

Then start the App server:
```
cd scramblies-server
clj -M:scramblies-server
```

And after that start the web UI:
```
cd scramblies-client
npx shadow-cljs watch app
```