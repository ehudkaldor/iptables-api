package org.krazykat.iptables.dsl

case class States(state: _State, states: _State*) {
  val set = (state +: states).toSet
  val string = " --state " + set.map(_.name.trim).mkString(",") + " "
  override def toString = string  
}

case class CTStates(ctState: _CTState, ctStates: _CTState*){
  val set = (ctState +: ctStates).toSet
  val string = " --state " + set.map(_.name.trim).mkString(",") + " "
  override def toString = string  
}


abstract class _State(name: String) extends _CTState(name)

abstract class _CTState(val name: String)

case class New() extends _State(" NEW ")
case class Established() extends _State(" ESTABLISHED ")
case class Related() extends _State(" RELATED ")
case class Invalid() extends _State(" INVALID ")
