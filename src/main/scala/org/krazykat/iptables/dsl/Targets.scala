package org.krazykat.iptables.dsl


abstract class _Target(val target: String) { implicit def targetToString = s" --jump $target "}
sealed abstract class _Policy(override val target: String) extends _Target(target) { implicit def policyToString = s" --policy $target "}
case class Jump(dest: String) extends _Target(dest)
case class Accept() extends _Policy("ACCEPT")
case class Reject() extends _Target("REJECT") 
case class Drop() extends _Policy("DROP") 
case class Log() extends _Target("LOG")

object TargetsImplicits {
  implicit def policyToString(p: _Policy): String = {
    p match {
      case Accept() => "ACCEPT"
      case Drop() => "DROP"
    }
  }
  
  implicit def targetToString(target: _Target): String = {
    target match {
      case Accept() => "ACCEPT"
      case Reject() => "REJECT"
      case Drop() => "DROP"
      case Log() => "LOG"
      case j: Jump => j.dest
    }
  }
}