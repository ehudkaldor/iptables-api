package org.krazykat.iptables.dsl

abstract class _Protocol(protocol: String)
case class TCP() extends _Protocol(" --protocol TCP ")
case class UDP() extends _Protocol(" --protocol UDP ")
case class ICMP() extends _Protocol(" --protocol ICMP ")

abstract class _EndPoint(direction: String)
case class InInterface(interface: String) extends _EndPoint(s" --in-interface $interface ")
case class OutInterface(interface: String) extends _EndPoint(s" --out-interface $interface ")
case class Source(address: String) extends _EndPoint(s" --source $address ")
case class Destination(address: String) extends _EndPoint(s" --destination $address ")