package org.krazykat.iptables.dsl

import org.specs2.mutable.Specification

class DSLspec extends Specification {
  
  "Generate iptables commands" should {
    "generate delete command with no table and rulenum 5" in {
      val command = Delete(Input(), RuleNum(5))
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --delete INPUT 5 ")
    }
    "generate delete command with table nat and rulenum 10" in {
      val command = Delete(Nat(), Output(), RuleNum(10))
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table nat --delete OUTPUT 10 ")
    }
  }
  
  "Generate flush commands" should {
    "generate flush command with table nat and chain INPUT" in {
      val command = Flush(Nat(), Input())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table nat --flush INPUT ")
    }
    "generate flush command with no table and chain FORWARD" in {
      val command = Flush(Forward())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --flush FORWARD ")
    }
    "generate flush command with no table and no chain" in {
      val command = Flush()
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --flush ")
    }
  }
  
  "Generate zero commands" should {
    "generate zero command with no table and no chain" in {
      val command = Zero()
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --zero ")
    }
    "generate zero command with table raw and no chain" in {
      val command = Zero(Raw())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table raw --zero ")
    }
    "generate zero command with no table and chain FORWARD" in {
      val command = Zero(Forward())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --zero FORWARD ")
    }
    "generate zero command with table nat and chain INPUT and no rule number" in {
      val command = Zero(Nat(), Input())
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table nat --zero INPUT ")
    }
    "generate zero command with table mangle and chain INPUT and rule num 3" in {
      val command = Zero(Mangle(), Input(), RuleNum(3))
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table mangle --zero INPUT 3 ")
    }
    "generate zero command with no table and chain OUTPUT and rule num 15" in {
      val command = Zero(Output(), RuleNum(15))
      command.command.replaceAll("\\s+"  , " ") must be equalTo(" iptables --table filter --zero OUTPUT 15 ")
    }
  }
}