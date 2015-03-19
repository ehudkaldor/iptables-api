package org.krazykat.iptables.dsl

abstract class _Table(table: String) { override def toString = table}
case class Filter() extends _Table("filter")
case class Nat() extends _Table("nat")
case class Mangle() extends _Table("mangle")
case class Raw() extends _Table("raw")
case class Security() extends _Table("security")
