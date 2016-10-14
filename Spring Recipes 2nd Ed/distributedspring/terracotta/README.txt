How to run these examples:

the manual way (using the dso-env.sh and so on) works fine for production. However, during development there are better, more convenient options, not the least of which is the Maven Terracotta plugin.

To run the examples you need only change the configuration file that's used and then run the server and then as many clients as you want.

I've included in this folder's bin directory a script called run.sh. This in turn simpply runs Maven and will invoke a server if need be.

usage looks like:

-----------------------------------------------------------

cd bin;
chmod a+x run.sh ;
./run.sh

-----------------------------------------------------------

