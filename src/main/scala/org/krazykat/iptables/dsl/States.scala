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

case class CreateChain(table: _Table, chainName: String)
object CreateChain {
  def apply(chainName: String): CreateChain = new CreateChain(Filter(), chainName)
}

case class DeleteChain(table: _Table, chain: _Chain)
object DeleteChain {
  def apply(chain: _Chain): DeleteChain = new DeleteChain(Filter(), chain)
}

case class RenameChain(table: _Table, chain: _Chain, newName: String)
object RenameChain {
  def apply(chain: _Chain, newName: String): RenameChain = new RenameChain(Filter(), chain, newName)
}
