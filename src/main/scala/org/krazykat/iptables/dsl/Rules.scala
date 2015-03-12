package org.krazykat.iptables.dsl

abstract class _Rule { override def toString = construct; def construct: String}
case class RuleSpec() extends _Rule { override def construct: String = "" }
case class RuleNum(num: Int) extends _Rule { override def construct: String = num.toString }
