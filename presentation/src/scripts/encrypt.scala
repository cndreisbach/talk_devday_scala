#!/bin/sh
exec scala $0 $@
!#

println(new String(args.deepMkString(" ").getBytes.map((b) => {
  if ((b >= 65 && b <= 77) || (b >= 97 && b <= 109))
    (b + 13).toByte
  else if ((b >= 78 && b <= 90) || (b >= 110 && b <= 122))
    (b - 13).toByte
  else
    b
})))

// > encrypt.scala I am Master Spy
// V nz Znfgre Fcl
