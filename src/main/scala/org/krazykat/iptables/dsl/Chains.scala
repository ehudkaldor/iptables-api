package org.krazykat.iptables.dsl

abstract class _Chain(name: String){ override def toString = name}
case class Input() extends _Chain(" INPUT ")
case class Forward() extends _Chain(" FORWARD ")
case class Output() extends _Chain(" OUTPUT ")
