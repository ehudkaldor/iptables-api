package org.krazykat.iptables.dsl

abstract class _Chain(val name: String)
sealed abstract class _BuiltInChain(name: String) extends _Chain(name)
case class Input() extends _BuiltInChain("INPUT")
case class Forward() extends _BuiltInChain("FORWARD")
case class Output() extends _BuiltInChain("OUTPUT")

case class NewChain(tableOpt: Option[_Table], chainName: String)
//object NewChain {
//  def apply(chainName: String): NewChain = new NewChain(Filter(), chainName)
//}

case class DeleteChain(tableOpt: Option[_Table], chain: _Chain)
//object DeleteChain {
//  def apply(chain: _Chain): DeleteChain = new DeleteChain(Filter(), chain)
//}

case class RenameChain(tableOpt: Option[_Table], chain: _Chain, newName: String)
//object RenameChain {
//  def apply(chain: _Chain, newName: String): RenameChain = new RenameChain(Filter(), chain, newName)
//}

case class SetPolicy(table: _Table, chain: _BuiltInChain, policy: _Policy)
object SetPolicy {
  def apply(chain: _BuiltInChain, policy: _Policy): SetPolicy = new SetPolicy(Filter(), chain, policy)  
}