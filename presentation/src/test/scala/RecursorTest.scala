import org.scalatest._

class RecursorTest extends FunSuite {
  test("non-tail calls blow up the stack") {
    try { Recursor.recurseNonTailCall(1) }
    catch {
      case ex => assert(
        ex.getStackTrace()(0).getMethodName == ex.getStackTrace()(1).getMethodName)
    }
  }

  test("tail calls are ninja") {
    try { Recursor.recurseTailCall(1) }
    catch {
      case ex => assert(
        ex.getStackTrace()(0).getMethodName != ex.getStackTrace()(1).getMethodName)
    }
  }
}
