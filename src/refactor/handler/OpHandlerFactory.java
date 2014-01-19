package refactor.handler;

import java.util.logging.Logger;

import org.jf.dexlib2.Opcode;
import org.jf.dexlib2.iface.instruction.Instruction;

import refactor.vm.VirtualMachine;
import simplify.Main;

public final class OpHandlerFactory {

    private static final Logger log = Logger.getLogger(Main.class.getSimpleName());

    private enum FactoryType {
        BINARY_MATH,
        CONST,
        IF,
        UNIMPLEMENTED
    };

    private final VirtualMachine vm;

    public OpHandlerFactory(VirtualMachine vm, String methodDescriptor) {
        // vm gives class and try/catch block access to factory type factories
        this.vm = vm;
    }

    public OpHandler create(Instruction instruction, int index) {
        OpHandler result = null;
        FactoryType factoryType = getFactoryType(instruction.getOpcode());
        switch (factoryType) {
        case BINARY_MATH:
            result = BinaryMathOpHandlerFactory.create(instruction, index);
            break;
        case CONST:
            result = ConstOpHandlerFactory.create(instruction, index);
            break;
        case IF:
            break;
        case UNIMPLEMENTED:
            result = UnimplementedOpHandlerFactory.create(instruction, index);
            break;
        }

        return result;
    }

    @SuppressWarnings("incomplete-switch")
    private static FactoryType getFactoryType(Opcode op) {
        FactoryType result = FactoryType.UNIMPLEMENTED;

        switch (op) {
        case ADD_DOUBLE:
        case ADD_DOUBLE_2ADDR:
        case ADD_FLOAT:
        case ADD_FLOAT_2ADDR:
        case ADD_INT:
        case ADD_INT_2ADDR:
        case ADD_INT_LIT16:
        case ADD_INT_LIT8:
        case ADD_LONG:
        case ADD_LONG_2ADDR:
        case AND_INT:
        case AND_INT_2ADDR:
        case AND_INT_LIT16:
        case AND_INT_LIT8:
        case AND_LONG:
        case AND_LONG_2ADDR:
        case DIV_DOUBLE:
        case DIV_DOUBLE_2ADDR:
        case DIV_FLOAT:
        case DIV_FLOAT_2ADDR:
        case DIV_INT:
        case DIV_INT_2ADDR:
        case DIV_INT_LIT16:
        case DIV_INT_LIT8:
        case DIV_LONG:
        case DIV_LONG_2ADDR:
        case MUL_DOUBLE:
        case MUL_DOUBLE_2ADDR:
        case MUL_FLOAT:
        case MUL_FLOAT_2ADDR:
        case MUL_INT:
        case MUL_INT_2ADDR:
        case MUL_INT_LIT16:
        case MUL_INT_LIT8:
        case MUL_LONG:
        case MUL_LONG_2ADDR:
        case OR_INT:
        case OR_INT_2ADDR:
        case OR_INT_LIT16:
        case OR_INT_LIT8:
        case OR_LONG:
        case OR_LONG_2ADDR:
        case REM_DOUBLE:
        case REM_DOUBLE_2ADDR:
        case REM_FLOAT:
        case REM_FLOAT_2ADDR:
        case REM_INT:
        case REM_INT_2ADDR:
        case REM_INT_LIT16:
        case REM_INT_LIT8:
        case REM_LONG:
        case REM_LONG_2ADDR:
        case RSUB_INT:
        case RSUB_INT_LIT8:
        case SHL_INT:
        case SHL_INT_2ADDR:
        case SHL_INT_LIT8:
        case SHL_LONG:
        case SHL_LONG_2ADDR:
        case SHR_INT:
        case SHR_INT_2ADDR:
        case SHR_INT_LIT8:
        case SHR_LONG:
        case SHR_LONG_2ADDR:
        case SUB_DOUBLE:
        case SUB_DOUBLE_2ADDR:
        case SUB_FLOAT:
        case SUB_FLOAT_2ADDR:
        case SUB_INT:
        case SUB_INT_2ADDR:
        case SUB_LONG:
        case SUB_LONG_2ADDR:
        case USHR_INT:
        case USHR_INT_2ADDR:
        case USHR_INT_LIT8:
        case USHR_LONG:
        case USHR_LONG_2ADDR:
        case XOR_INT:
        case XOR_INT_2ADDR:
        case XOR_INT_LIT16:
        case XOR_INT_LIT8:
        case XOR_LONG:
        case XOR_LONG_2ADDR:
            result = FactoryType.BINARY_MATH;
            break;

        case AGET:
        case AGET_BOOLEAN:
        case AGET_BYTE:
        case AGET_CHAR:
        case AGET_OBJECT:
        case AGET_SHORT:
        case AGET_WIDE:
            break;

        case APUT:
        case APUT_BOOLEAN:
        case APUT_BYTE:
        case APUT_CHAR:
        case APUT_OBJECT:
        case APUT_SHORT:
        case APUT_WIDE:
            break;

        case ARRAY_LENGTH:
            break;
        case ARRAY_PAYLOAD:
            break;
        case CHECK_CAST:
            break;

        case CMPG_DOUBLE:
        case CMPG_FLOAT:
        case CMPL_DOUBLE:
        case CMPL_FLOAT:
        case CMP_LONG:
            break;

        case CONST: // 31i
        case CONST_16: // 21s
        case CONST_4: // 11n
        case CONST_CLASS:
        case CONST_HIGH16:
        case CONST_STRING:
        case CONST_STRING_JUMBO:
        case CONST_WIDE:
        case CONST_WIDE_16:
        case CONST_WIDE_32:
        case CONST_WIDE_HIGH16:
            // result = FactoryType.CONST;
            break;

        case DOUBLE_TO_FLOAT:
        case DOUBLE_TO_INT:
        case DOUBLE_TO_LONG:
        case FLOAT_TO_DOUBLE:
        case FLOAT_TO_INT:
        case FLOAT_TO_LONG:
        case INT_TO_BYTE:
        case INT_TO_CHAR:
        case INT_TO_DOUBLE:
        case INT_TO_FLOAT:
        case INT_TO_LONG:
        case INT_TO_SHORT:
        case LONG_TO_DOUBLE:
        case LONG_TO_FLOAT:
        case LONG_TO_INT:
            break;

        case FILLED_NEW_ARRAY:
            break;
        case FILLED_NEW_ARRAY_RANGE:
            break;
        case FILL_ARRAY_DATA:
            break;

        case GOTO:
        case GOTO_16:
        case GOTO_32:
            break;

        case IF_EQ:
        case IF_GE:
        case IF_GT:
        case IF_LE:
        case IF_LT:
        case IF_NE:
        case IF_EQZ:
        case IF_GEZ:
        case IF_GTZ:
        case IF_LEZ:
        case IF_LTZ:
        case IF_NEZ:
            result = FactoryType.IF;
            break;

        case IGET:
        case IGET_BOOLEAN:
        case IGET_BYTE:
        case IGET_CHAR:
        case IGET_OBJECT:
        case IGET_SHORT:
        case IGET_WIDE:
            break;

        case INSTANCE_OF:
            break;

        case INVOKE_DIRECT:
        case INVOKE_INTERFACE:
        case INVOKE_STATIC:
        case INVOKE_SUPER:
        case INVOKE_VIRTUAL:
        case INVOKE_DIRECT_RANGE:
        case INVOKE_INTERFACE_RANGE:
        case INVOKE_STATIC_RANGE:
        case INVOKE_SUPER_RANGE:
        case INVOKE_VIRTUAL_RANGE:
            break;

        case IPUT:
        case IPUT_BOOLEAN:
        case IPUT_BYTE:
        case IPUT_CHAR:
        case IPUT_OBJECT:
        case IPUT_SHORT:
        case IPUT_WIDE:
            break;

        case MONITOR_ENTER:
            break;
        case MONITOR_EXIT:
            break;
        case MOVE:
        case MOVE_16:
        case MOVE_FROM16:
        case MOVE_OBJECT:
        case MOVE_OBJECT_16:
        case MOVE_OBJECT_FROM16:
        case MOVE_WIDE:
        case MOVE_WIDE_16:
        case MOVE_WIDE_FROM16:
            break;

        case MOVE_EXCEPTION:
            break;
        case MOVE_RESULT:
        case MOVE_RESULT_OBJECT:
        case MOVE_RESULT_WIDE:
            break;
        case NEG_DOUBLE:
            break;
        case NEG_FLOAT:
            break;
        case NEG_INT:
            break;
        case NEG_LONG:
            break;
        case NEW_ARRAY:
            break;
        case NEW_INSTANCE:
            break;

        case NOP:
            break;
        case NOT_INT:
            break;
        case NOT_LONG:
            break;
        case PACKED_SWITCH:
            break;
        case PACKED_SWITCH_PAYLOAD:
            break;
        case RETURN:
        case RETURN_WIDE:
        case RETURN_OBJECT:
            break;
        case RETURN_VOID:
            break;
        case SGET:
        case SGET_BOOLEAN:
        case SGET_BYTE:
        case SGET_CHAR:
        case SGET_OBJECT:
        case SGET_SHORT:
        case SGET_WIDE:
            break;

        case SPARSE_SWITCH:
            break;
        case SPARSE_SWITCH_PAYLOAD:
            break;
        case SPUT:
        case SPUT_BOOLEAN:
        case SPUT_BYTE:
        case SPUT_CHAR:
        case SPUT_OBJECT:
        case SPUT_SHORT:
        case SPUT_WIDE:
            break;
        case THROW:
            break;
        }

        return result;
    }

}