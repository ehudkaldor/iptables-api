package org.krazykat.iptables.dsl

/****************************************
 *                                      *
 *              Commands                *
 *                                      *
 ****************************************/
abstract class _Command(val command: String) { override def toString = command}

case class Append(table: _Table, chain: _Chain, ruleSpec: RuleSpec) extends _Command(s" iptables --table $table --append $chain $ruleSpec ")
object Append {
  def apply(chain: _Chain, ruleSpec: RuleSpec): Delete = new Delete(Filter(), chain, ruleSpec) 
}

case class Check(table: _Table, chain: _Chain, ruleSpec: RuleSpec) extends _Command(s" iptables --table $table --check $chain $ruleSpec ")
object Check {
  def apply(chain: _Chain, ruleSpec: RuleSpec): Check = new Check(Filter(), chain, ruleSpec) 
}

case class Delete(table: _Table, chain: _Chain, rule: _Rule) extends _Command(s" iptables --table $table --delete $chain $rule ")
object Delete {
  def apply(chain: _Chain, rule: _Rule): Delete = new Delete(Filter(), chain, rule) 
}

case class Replace(table: _Table, chain: _Chain, ruleNum: RuleNum, ruleSpec: RuleSpec) extends _Command(s" iptables --table $table --delete $chain $ruleNum $ruleSpec ")
object Replace {
  def apply(chain: _Chain, ruleNum: RuleNum, ruleSpec: RuleSpec): Replace = new Replace(Filter(), chain, ruleNum, ruleSpec)  
}

case class Insert(table: _Table, chain: _Chain, ruleNum: RuleNum, ruleSpec: RuleSpec) extends _Command(s" iptables --table $table --insert $chain $ruleNum $ruleSpec ")
object Insert {
  def apply(chain: _Chain, ruleSpec: RuleSpec): Insert = new Insert(Filter(), chain, RuleNum(1), ruleSpec) 
  def apply(table: _Table, chain: _Chain, ruleSpec: RuleSpec): Insert = new Insert(table, chain, RuleNum(1), ruleSpec) 
}

case class Flush(table: _Table, chain: _Chain) extends _Command(s" iptables --table $table --flush $chain ")
object Flush {
  def apply(chain: _Chain): Flush = new Flush(Filter(), chain)
  def apply(): Flush = new Flush(Filter(), null) { override val command = s" iptables --table $table --flush " }
}

case class Zero(table: _Table, chain: _Chain, ruleNum: RuleNum) extends _Command(s" iptables --table $table --zero $chain $ruleNum ")
object Zero {
  def apply(chain: _Chain, ruleNum: RuleNum): Zero = new Zero(Filter(), chain, ruleNum)
  def apply(chain: _Chain): Zero = new Zero(Filter(), chain, null)  { override val command = s" iptables --table $table --zero $chain " }
  def apply(table: _Table, chain: _Chain): Zero = new Zero(table, chain, null)  { override val command = s" iptables --table $table --zero $chain " }
  def apply(table: _Table): Zero = new Zero(table, null, null) { override val command = s" iptables --table $table --zero " }
  def apply(): Zero = new Zero(Filter(), null, null) { override val command = s" iptables --table $table --zero " }
}

abstract class _Jump(target: String)
case class Jump(dest: String) extends _Jump(dest)
case class Accept() extends _Jump(" -j ACCEPT ")
case class Reject() extends _Jump(" -j REJECT ")
case class Drop() extends _Jump(" -j DROP ")
case class Log() extends _Jump(" -j LOG ")

abstract class _Protocol(protocol: String)
case class TCP() extends _Protocol(" --protocol TCP ")
case class UDP() extends _Protocol(" --protocol UDP ")
case class ICMP() extends _Protocol(" --protocol ICMP ")

abstract class _EndPoint(direction: String)
case class InInterface(interface: String) extends _EndPoint(s" --in-interface $interface ")
case class OutInterface(interface: String) extends _EndPoint(s" --out-interface $interface ")
case class Source(address: String) extends _EndPoint(s" --source $address ")
case class Destination(address: String) extends _EndPoint(s" --destination $address ")

abstract class _State(name: String)
case class New() extends _State(" --state NEW ")
case class Established() extends _State(" --state ESTABLISHED ")
case class Related() extends _State(" --state RELATED ")
case class Invalid() extends _State(" --state INVALID ")
