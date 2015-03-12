package org.krazykat.iptables.dsl

abstract class _State(val name: String)
case class State(state: _State, states: _State*) {
  val string = " " + (state +: states).map(_.name.trim).mkString(",") + " "
  override def toString = string  
}
case class New() extends _State(" NEW ")
case class Established() extends _State(" ESTABLISHED ")
case class Related() extends _State(" RELATED ")
case class Invalid() extends _State(" INVALID ")
