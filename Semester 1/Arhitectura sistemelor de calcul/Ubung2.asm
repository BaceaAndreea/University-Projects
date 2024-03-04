bits 32 
global start        


extern exit, printf, scanf, fopen, fclose, fprintf, fread    
import exit msvcrt.dll    
import printf msvcrt.dll
import scanf msvcrt.dll 
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll
import fread msvcrt.dll


global start        

extern exit               
import exit msvcrt.dll    

segment data use32 class=data
    s db "Oana~! are mer1e/"
    nou times 100 db 0
    format db "%s"
segment code use32 class=code
    start:
        mov edx, 17 ; length of sing
        mov ecx, 0 ; sing to process
        mov ebx, 0; destination sing

        next_char:
            mov al, [s+ecx] ; load character from source sing
            cmp edx,0
            je sfarsit
            cmp al, 'A' ; check if character is special
            jl double_char ; if character is less than 'A', it's special
            cmp al, 'Z'
            jl no_double
            cmp al, 'a'
            jl double_char
            cmp al, 'z'
            jg double_char

        no_double:
            mov [nou+ebx], al ; copy character to destination sing
            inc ebx ; increment destination pointer
            inc ecx ; increment source pointer
            dec edx ; decrement remaining characters
            jnz next_char ; repeat for next character

        double_char:
            mov [nou+ebx], al ; copy first instance of character to destination
            inc ebx ; increment destination pointer
            mov [nou+ebx], al ; copy second instance of character to destination
            inc ebx ; increment destination pointer
            inc ecx ; increment source pointer
            dec edx ; decrement remaining characters
            jnz next_char ; repeat for next character

        sfarsit:
        push dword nou
        push dword format
        call [printf]
        add esp, 4*2 
    
    ; exit(0)
    push    dword 0      ; push the parameter for exit onto the stack
    call    [exit]       ; call exit to terminate the program