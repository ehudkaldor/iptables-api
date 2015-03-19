package org.krazykat.iptables.dsl

abstract class _Rule
case class RuleSpec() extends _Rule
case class RuleNum(num: Int) extends _Rule
