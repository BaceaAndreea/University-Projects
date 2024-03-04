bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

extern exit,printf ,fopen,fclose,fread,scanf,fprintf             
import exit msvcrt.dll 
import printf msvcrt.dll 
import fopen msvcrt.dll 
import scanf msvcrt.dll 
import fclose msvcrt.dll 
import fread msvcrt.dll 
import fprintf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    ; Text from file
    file_name db "prufung712.txt", 0
    access_mode db "r", 0
    file_descriptor dd -1
    len equ 100
    text times len db 0
    nr_car_citite dd 0 ; variabila in care vomsalva numarul de caractere citit din fisier in etapa curenta
    buffer resb len ; sirul in care se va citi textul din fisier
    
    cifrepare dd 0
    format db "au fost gasite %d numere pare",0
    
; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword access_mode
        push dword file_name
        call [fopen]
        add esp,4*2
        
        cmp eax,0
        je final
        mov [file_descriptor],eax
        readfile:
            push dword [file_descriptor]
            push dword len
            push dword 1
            push dword buffer
            call [fread]
            add esp,4*4
            
            cmp eax,0
            je Cleanup
            
            mov [nr_car_citite],eax
            jmp readfile
            
        Cleanup:
            push dword [file_descriptor]
            call [fclose]
            add esp,4
            
        final:
            mov esi,0
            mov ebx,[nr_car_citite]
            
        start_loop:
            cmp ebx,0
            je end_loop
            
            mov edx,0
            mov dl,[buffer+esi]
            cmp dl,'0'
            jl Nodigit
            cmp dl,'9'
            jg Nodigit
            mov al,dl
            sub al,'0'
            test al,1
            jnz notEven ;Zf=0
            inc dword [cifrepare]
            inc esi
            dec ebx
            jmp start_loop
            notEven:
            Nodigit:
                inc esi
                dec ebx
                jmp start_loop
        end_loop:
        push dword[cifrepare]
        push dword format
        call [printf]
        add esp,4*2
            
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
