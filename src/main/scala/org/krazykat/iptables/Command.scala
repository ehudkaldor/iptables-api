package org.krazykat.iptables

import dsl._

case class Command(table: _Table, chain: _Chain) {

}

object Command {
  def apply(chain: _Chain): Command = new Command(Filter(), chain)
}