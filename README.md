# iptables-api

iptables-api is a Scala envelope around the Iptables Linux firewall. It will probably start with a subset of the options to manage the filewall, and will be expanded based on need. Commands will be run using Process (import sys.process._).

the base level is a case-class-based DSL to wrap around the Iptables building blocks, and build off it. So, a command like 
```
iptables -t nat -A INPUT -p tcp --dport 80 -j ACCEPT
```
will be
```
case class Nat() extends _Table()
case class Input() extends _Chain()
case class Tcp() extends _Protocol()
case class Accept() extends _Target()

Append(Nat(), Input(), Tcp(), DPort(80), Accept())
```

